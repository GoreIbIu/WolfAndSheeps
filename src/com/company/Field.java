package com.company;

public class Field {
     int[][] cells = new int[7][7];

    public void fill(){
        cells[0][0]=-1;
        cells[0][1]=-1;
        cells[1][0]=-1;
        cells[1][1]=-1;
        cells[0][6]=-1;
        cells[0][5]=-1;
        cells[1][6]=-1;
        cells[1][5]=-1;
        cells[6][6]=-1;
        cells[6][5]=-1;
        cells[5][6]=-1;
        cells[5][5]=-1;
        cells[5][0]=-1;
        cells[5][1]=-1;
        cells[6][0]=-1;
        cells[6][1]=-1;
        cells[0][2]=0;
        cells[0][3]=0;
        cells[0][4]=0;
        cells[1][2]=0;
        cells[1][3]=0;
        cells[1][4]=0;
        cells[5][2]=0;
        cells[5][3]=0;
        cells[5][4]=0;
        cells[6][2]=0;
        cells[6][3]=0;
        cells[6][4]=0;
        for(int i = 2;i<5;i++){
            for(int j=0;j<7;j++){
                cells[i][j] = 0;
            }
        }

    }
    public void view(){
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                System.out.print(" ");
                char ch='-';
                if(cells[i][j]==-1){ch='#';}
                if(cells[i][j]==0){ch='-';}
                if(cells[i][j]==1){ch='*';}
                if(cells[i][j]==2){ch='^';}
                System.out.print(""+ch);
            }
            System.out.println();
        }
    }

}
