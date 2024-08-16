#!/bin/bash

# 문제 번호 입력 받기
read -p "문제 번호를 입력하세요: " problem_number
read -p "문제 번호를 입력하세요: " problem_number
read -p "문제 번호를 입력하세요: " problem_number
read -p "문제 번호를 입력하세요: " problem_number
# 로컬 브랜치 이름 생성
branch_name="${problem_number}"

# Git 작업 수행
git fetch origin main:feature
git pull origin main
git branch $branch_name
git checkout $branch_name
git add -A
git commit -m "solved LeetCode $problem_number. Valid Palindrome - 16ms 2060kb"
git push algorithm $branch_name