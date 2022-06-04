
func expand(s string) []string {
    blocks := [][]string{}
    i := 0
    for i < len(s) {
        block := []string{}
        if s[i] == '{' {
            i++
            for s[i] != '}' {
                if s[i] != ',' {
                    block = append(block, string(s[i]))
                }
                i++
            }
        } else {
            block = append(block, string(s[i]))
        }
        sort.Strings(block)
        blocks = append(blocks, block)
        i++
    }
    result := []string{}
    var backtrack func(start int, path string)
    backtrack = func(start int, path string) {
        // base
        if start == len(blocks) {
            result = append(result, path)
            return
        }
        
        // logic
        for i := 0; i < len(blocks[start]); i++ {
            path += blocks[start][i]
            backtrack(start+1, path)
            path = path[:len(path)-1]
        }
    }
    backtrack(0, "")
    return result
}
