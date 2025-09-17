# scripts/update_readme.py (src 폴더 인식 버전)

import os
import re
from collections import defaultdict

# ----- 여기만 수정! -----
# 검색을 시작할 최상위 폴더를 'src'로 지정합니다.
# 만약 src 폴더를 사용하지 않으려면 '.' 으로 바꾸면 됩니다.
SEARCH_DIR = 'src'
# -------------------------

# 무시할 폴더 목록
IGNORED_DIRS = {'.git', '.github', 'scripts'}

def count_problems():
    stats = defaultdict(int)
    total_count = 0

    # 검색 시작 폴더가 존재하지 않으면 빈 결과를 반환
    if not os.path.isdir(SEARCH_DIR):
        print(f"'{SEARCH_DIR}' directory not found. Skipping statistics update.")
        return stats, total_count

    # 지정된 SEARCH_DIR 부터 탐색 시작
    for root, dirs, files in os.walk(SEARCH_DIR):
        # 무시할 폴더는 건너뛰기
        dirs[:] = [d for d in dirs if d not in IGNORED_DIRS]

        path_parts = root.split(os.sep)

        # 'src/플랫폼/난이도/문제폴더' (길이 4) 또는 'src/플랫폼/문제폴더' (길이 3) 구조
        if len(path_parts) >= 3:
            if any(f.endswith('.java') or f.endswith('.md') for f in files):
                platform = path_parts[1] # 'Baekjoon' 또는 'Programmers'
                stats[platform] += 1
                total_count += 1
                
                dirs[:] = [] # 중복 카운트 방지

    return stats, total_count

def generate_stats_table(stats, total_count):
    table = "| 플랫폼 | 해결한 문제 수 |\n"
    table += "| :---: | :---: |\n"
    
    for platform in sorted(stats.keys()):
        table += f"| **{platform}** | {stats[platform]} |\n"
        
    table += f"| **총합 (Total)** | **{total_count}** |\n"
    return table

def update_readme(stats_table):
    readme_path = 'README.md'
    with open(readme_path, 'r', encoding='utf-8') as f:
        content = f.read()

    new_content = re.sub(
        r"(.|\n)*",
        f"\n{stats_table}\n",
        content
    )

    with open(readme_path, 'w', encoding='utf-8') as f:
        f.write(new_content)

if __name__ == "__main__":
    problem_stats, total = count_problems()
    markdown_table = generate_stats_table(problem_stats, total)
    update_readme(markdown_table)
    print("README.md updated successfully!")