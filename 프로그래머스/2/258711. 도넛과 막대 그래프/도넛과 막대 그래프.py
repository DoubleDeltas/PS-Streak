def solution(edges):
    M = 1_000_000
    
    in_deg = [0]*(M+1)
    out_deg = [0]*(M+1)
    start = 0
    graph = {}
    donut, stick, eight = 0, 0, 0
    
    for v1, v2 in edges:
        out_deg[v1] += 1
        in_deg[v2] += 1
        if v1 in graph:
            graph[v1].append(v2)
        else:
            graph[v1] = [v2]
    
    for i in range(1, M+1):
        # 입력간선이 없고 출력간선이 2 이상 있으면 추가된 간선
        if not in_deg[i] and out_deg[i] >= 2:
            start = i
            break
            
    for s in graph[start]:
        # 탐색 시작 노드가 막대의 마지막
        if out_deg[s] == 0:
            stick += 1
            continue
        
        v = graph[s][0]
        while True:
            # 막대의 끝
            if out_deg[v] == 0:
                stick += 1
                break
            # 8자 그래프의 중심점을 만남
            if out_deg[v] == 2:
                eight += 1
                break
            # 도넛 그래프 돌아옴
            if v == s:
                donut += 1
                break
            
            v = graph[v][0]
        
    
    return [start, donut, stick, eight]
    