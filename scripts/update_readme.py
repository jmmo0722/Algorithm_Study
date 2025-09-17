# scripts/update_readme.py (디버깅 모드 최종 버전)

import os
import re
from collections import defaultdict

# 검색을 시작할 경로
SEARCH_DIR = 'src/main/java'

def count_problems():
    # --- 디버깅 출력 시작 ---
    print("--- STARTING DEBUG MODE ---")
    print(f"Current Working Directory: {os.getcwd()}")

    # 현재 디렉토리의 모든 파일 및 폴더 목록 출력
    print("\nListing files in current directory ('.'):")
    for item in os.listdir('.'):
        print(f"- {item}")

    stats = defaultdict(int)
    total_count = 0

    if not os.path.isdir(SEARCH_DIR):
        print(f"\n!!! CRITICAL ERROR: Search directory '{SEARCH_DIR}' was not found.")
        return stats, total_count

    print(f"\nSuccessfully found search directory: '{SEARCH_DIR}'")
    print(f"Listing contents of '{SEARCH_DIR}':")
    for item in os.listdir(SEARCH_DIR):
        print(f"- {item}")

    platforms = [d for d in os.listdir(SEARCH_DIR) if os.path.isdir(os.path.join(SEARCH_DIR, d))]
    print(f"\nIdentified platform directories: {platforms}")

    for platform in platforms:
        print(f"\n--- Scanning platform: {platform} ---")
        platform_path = os.path.join(SEARCH_DIR, platform)

        for root, dirs, files in os.walk(platform_path):
            print(f"  - Visiting directory: {root}")
            if any(f.endswith('.java') or f.endswith('.md') for f in files):
                print(f"    -> SUCCESS: Found problem files! {files}")
                stats[platform] += 1
                total_count += 1
                dirs[:] = []

    print("\n--- DEBUG FINISHED ---")
    print(f"Final stats calculated: {dict(stats)}")
    print(f"Total problems found: {total_count}")
    # --- 디버깅 출력 끝 ---
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
        content)
    with open(readme_path, 'w', encoding='utf-8') as f:
        f.write(new_content)

if __name__ == "__main__":
    problem_stats, total = count_problems()
    markdown_table = generate_stats_table(problem_stats, total)
    update_readme(markdown_table)
    print("\nREADME.md updated successfully!")