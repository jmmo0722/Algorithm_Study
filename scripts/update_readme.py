# scripts/update_readme.py (최종 안전 버전)

import os
from collections import defaultdict

SEARCH_DIR = 'src/main/java'

def count_problems():
    stats = defaultdict(int)
    total_count = 0
    if not os.path.isdir(SEARCH_DIR):
        return stats, total_count

    platforms = [d for d in os.listdir(SEARCH_DIR) if os.path.isdir(os.path.join(SEARCH_DIR, d))]
    for platform in platforms:
        platform_path = os.path.join(SEARCH_DIR, platform)
        for root, dirs, files in os.walk(platform_path):
            if any(f.endswith('.java') or f.endswith('.md') for f in files):
                stats[platform] += 1
                total_count += 1
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
    start_marker = ""
    end_marker = ""

    with open(readme_path, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    new_lines = []
    in_stats_block = False
    stats_block_written = False

    for line in lines:
        if start_marker in line:
            in_stats_block = True
            new_lines.append(line)
            if not stats_block_written:
                # 새로운 통계 테이블을 추가하고, 줄바꿈을 추가합니다.
                new_lines.append(stats_table + '\n')
                stats_block_written = True
        elif end_marker in line:
            in_stats_block = False
            new_lines.append(line)
        elif not in_stats_block:
            new_lines.append(line)

    with open(readme_path, 'w', encoding='utf-8') as f:
        f.writelines(new_lines)

if __name__ == "__main__":
    problem_stats, total = count_problems()
    markdown_table = generate_stats_table(problem_stats, total)
    update_readme(markdown_table)
    print("README.md updated successfully with the safe script!")