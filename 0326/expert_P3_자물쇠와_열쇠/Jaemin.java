class Jaemin {
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static int N, M;

    public boolean solution(int[][] key , int[][] lock){
        N = key.length;
        M = lock.length;
        int[][] turnKey = key;
        for(int a = 0; a < 4; a++){

            for(int i = 1 -N; i < M ; i++){
                for(int j = 1 -N; j < M; j++){
                    int[][] checkLock = new int[M][M];
                    for(int l = 0; l < M; l++){
                        System.arraycopy(lock[l], 0, checkLock[l], 0, M);
                    }

                    if(check(turnKey, checkLock, i , j)) return true;
                }
            }
            turnKey = turn(turnKey);
        }


        return  false;
    }

    static boolean check(int[][] key, int[][] lock, int sr, int sc){
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++){
                int lr = sr + i;
                int lc = sc + j;
                if(lr >= 0 && lr < M && lc >= 0 && lc < M ){
                    lock[lr][lc] += key[i][j];
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if(lock[i][j] != 1) return false;
            }
        }


        return true;
    }


    static int[][] move(int d, int[][] key){
        int[][] temp = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int nr = i + dr[d];
                int nc = j + dc[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N){
                    temp[i][j] = key[nr][nc];
                }
            }

        }
        return temp;
    }

    static int[][] turn (int[][] key){
        int[][] temp = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                temp[j][N -1 -i] = key[i][j];
            }
        }
        return temp;
    }
}