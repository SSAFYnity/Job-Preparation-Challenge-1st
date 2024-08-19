function solution(s) {
    
    let [m, n] = [s.length, s.length];
    
    while (true) {
        
        for (let i = 0; i < m - n + 1; i++) {
            let ss = s.slice(i, i + n);
            let l = 0;
            let r = ss.length - 1;
            let found = true;
            
            while (l < r) {
                if (ss[l] !== ss[r]) {
                    found = false;
                    break; 
                }
                l++;
                r--;
            }
            
            if (found) {
                return ss.length;
            }
            
        }
        
        n--;
    }
    
}
