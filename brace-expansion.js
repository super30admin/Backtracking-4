// Time Complexity : O(K^(N/K)) Where N is the length of the string and K is the number of groupings I have
// Space Complexity : O(N) Where N is the length of the string
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

/**
 * @param {string} S
 * @return {string[]}
 */
let result;
const expand = (S) => {
    result = [];
    const choiceList = parseList(S);
    backtrack(choiceList, [], 0);
    return result.sort();
};
    
const parseList = (S) => {
    const choiceList = [];
    let index = 0;

    while (index < S.length) {
        let currChoices = [];
        if (S[index] == '{') {
            index++;
            while (S[index] != '}') {
                if (S[index] != ',') currChoices.push(S[index]);
                index++;
            }
        } else {
            currChoices.push(S[index]);
        }
        choiceList.push(currChoices);
        index++;
    }
    
    return choiceList;
}

const backtrack = (choiceList, temp, index) => {
    // Base case
    if (temp.length >= choiceList.length) {
        result.push(temp.join(''));
        return;
    }
    
    // Logic
    for (let i = 0; i < choiceList[index].length; i++) {
        // Action
        temp.push(choiceList[index][i])
        // Recurse
        backtrack(choiceList, temp, index + 1);
        // Backtrack
        temp.pop();
    }
}
