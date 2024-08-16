import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        String S=br.readLine();

        int[] alphas=new int[26];

        for(int n=0;n<N;n++){
            if(n==N/2 & N%2!=0){
                continue;
            }

            alphas[S.charAt(n)-'a']+=1;

        }

        for(int i=0;i<alphas.length;i++){
            if(alphas[i]%2!=0){
                System.out.println("No");
                break;
            }

            if(i==(alphas.length-1)){
                System.out.println("Yes");
            }
        }

        br.close();
    }
}
