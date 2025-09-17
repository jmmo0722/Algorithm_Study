# scripts/update_readme.py (최종 수정본)

import os
import re
from collections import defaultdict

# 검색을 시작할 경로
SEARCH_DIR = 'src/main/java'

def count_problems():
    stats = defaultdict(int)
    total_count = 0

    # 검색 시작 폴더가 없으면 종료
    if not os.path.isdir(SEARCH_DIR):
        print(f"'{SEARCH_DIR}' directory not found.")
        return stats, total_count

    # 1. 플랫폼 폴더 목록을 찾는다 (예: Baekjoon, Programmers)
    platforms = [d for d in os.listdir(SEARCH_DIR) if os.path.isdir(os.path.join(SEARCH_DIR, d))]

    # 2. 각 플랫폼 폴더를 순회한다
    for platform in platforms:
        platform_path = os.path.join(SEARCH_DIR, platform)

        # 3. 플랫폼 폴더 하위의 모든 폴더를 탐색한다
        for root, dirs, files in os.walk(platform_path):
            # 4. 폴더 안에 .java 또는 .md 파일이 있으면 '문제 1개'로 카운트한다
            if any(f.endswith('.java') or f.endswith('.md') for f in files):
                stats[platform] += 1
                total_count += 1

                # 해당 폴더의 하위는 더 이상 탐색하지 않는다 (중복 방지)
                dirs[:] = []

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