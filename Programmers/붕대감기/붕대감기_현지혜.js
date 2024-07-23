                                // bandage = [기술시전시간, 1초당 회복량, 추가 회복량]
                                // health = 최대 체력
                                // attacks = [[몬스터 공격시간, 피해량],[,],[,]]
                function solution(bandage, health, attacks) {
                    const finalAttack = attacks[attacks.length-1][0]; // 마지막 공격 시간
                    const attacksLst = new Array(attacks.length).fill(0);
                    
                    // 순회길이만큼 몬스터 공격 리스트 재 설정
                    attacks.forEach(attack => { 
                        const time = attack[0];
                        const damage = attack[1];
                        attacksLst[time] = damage; 
                    })
                
                    let chanceTime = 0; // 연속 성공 체크
                    let maxHealth = health; // 최대 체력
                    
                    // 공격받은 총 시간동안 순회
                    for (let t = 0; t<=finalAttack; t++){
                        const [chanceTimeTarget,commonPower,bonusPower] = bandage;
                        
                        // (1) 공격 받는 경우
                        if (attacksLst[t] > 0){
                            health -= attacksLst[t];
                            if (health <= 0){ // 체력 0이하가 되면
                                break;
                            }
                            chanceTime = 0; // 연속 성공 초기화
                        }else{
                        // (2) 공격 안 받는 경우
                            if (health + commonPower <= maxHealth){
                                health += commonPower;
                            }else{
                                health = maxHealth;
                            }
                            if (t >0){
                                chanceTime += 1;
                            }
                            // 연속 성공을 시전 시간까지 채우면
                            if (chanceTime === chanceTimeTarget ){
                                chanceTime = 0; // 연속 성공 초기화
                                if (health + bonusPower <= maxHealth){
                                health += bonusPower; // 최대 체력 내에서 보너스 체력도 더해주기.
                                }else{
                                    health = maxHealth;
                                }
                            }
                        }
                        
                    }
                    
                    return health <=0 ? -1 : health;
                    // 모든 공격 끝난 직후 남은 체력( 체력 0 이하 시 -1 return)
                }
