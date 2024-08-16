#!/bin/bash

# 문제 번호 입력 받기
name_val="여동훈"

read -p "문제명을 입력하세요: " problem_name
read -p "걸린 시간을 입력하세요: " time
read -p "사용한 메모리를 입력하세요: " memory

# 로컬 브랜치 이름 생성
branch_name="$name_val'_'$problem_name"

# Git 작업 수행
# git fetch algorithm main:master  # 필요에 따라 주석 해제
# git pull algorithm main       # 필요에 따라 주석 해제
git branch $branch_name
git checkout $branch_name
git add .  # 필요한 파일만 추가하도록 수정
git commit -m "solved $problem_name - $time'ms' $memory'kb'"
git push algorithm $branch_name