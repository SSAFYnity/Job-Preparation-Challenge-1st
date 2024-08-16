#!/bin/bash

# 문제 번호 입력 받기
name_val = "여동훈"

read -p "문제명을 입력하세요: " problem_name
read -p "걸린 시간을 입력하세요: " time
read -p "사용한 메모리를 입력하세요: " memory

# 로컬 브랜치 이름 생성
branch_name= $name_val'_'$problem_name

# Git 작업 수행
# git fetch algorithm main:master
# git pull algorithm main
git branch $branch_name
git checkout $branch_name
git add .
git commit -m "solved $problem_name - $time $memory"
git push algorithm $branch_name