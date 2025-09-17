import os
import re
from collections import defaultdict

# 무시할 폴더 목록 (필요에 따라 추가)
IGNORED_DIRS = {'.git', '.github', 'scripts'}

def count_problems():
    stats = defaultdict(int)
    total_count = 0

    # 현재 작업 디렉토리부터 시작
    for root, dirs, files in os.walk('.'):
        # 무시할 폴더는 건너뛰기
        dirs[:] = [d for d in dirs if d not in IGNORED_DIRS]

        # 현재 폴더가 플랫폼 폴더 바로 아래에 있는지 확인
        # 예: ./Baekjoon/11053. 가장 긴 증가하는 부분 수열
        path_parts = root.split(os.sep)

        if len(path_parts) > 2: # './플랫폼/문제폴더' 구조
            # .java 또는 .md 파일이 있으면 문제 폴더라고 간주
            if any(f.endswith('.java') or f.endswith('.md') for f in files):
                platform = path_parts[1]
                stats[platform] += 1
                total_count += 1

    return stats, total_count

def generate_stats_table(stats, total_count):
    # 마크다운 테이블 생성
    table = "| 플랫폼 | 해결한 문제 수 |\n"
    table += "| :---: | :---: |\n"
    
    # 플랫폼 이름을 기준으로 정렬하여 일관된 순서 유지
    for platform in sorted(stats.keys()):
        table += f"| **{platform}** | {stats[platform]} |\n"
        
    table += f"| **총합 (Total)** | **{total_count}** |\n"
    return table

def update_readme(stats_table):
    readme_path = 'README.md'
    with open(readme_path, 'r', encoding='utf-8') as f:
        content = f.read()

    # 주석 사이의 내용을 새로운 통계 테이블로 교체
    # re.DOTALL 옵션은 여러 줄에 걸친 패턴을 찾을 수 있게 함
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
