def convert(s, numRows):
    """
    :type s: str
    :type numRows: int
    :rtype: str
    """
    if numRows == 1: 
        return s
    res = [[] for i in range(numRows)]
    cnt = 1
    zig_zag_dir = 1
    for c in s:
        print(cnt - 1)
        res[cnt - 1].append(c)
        cnt += zig_zag_dir
        if cnt == numRows or cnt == 1:
            zig_zag_dir *= -1
        
    return ''.join(e for res_l in res for e in res_l)

print(convert('ab', 1))