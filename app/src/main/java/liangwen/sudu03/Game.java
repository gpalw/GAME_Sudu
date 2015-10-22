package liangwen.sudu03;

import android.view.MotionEvent;

/**
 * Created by lenovo on 2015/7/25.
 */
public class Game {

    //初始化 九宫格的数据
    private final String initStr =
            "360000000004230800000004200"+
                    "070460003820000014500013020"+
                    "001900000007048300000000045";

    //定义一个 数组 存放 初始化数据，首先 要将 initStr里面的数据分离开，存放在数组里
    private int[] shuduku = new int[9*9] ;
    //单元格被使用的数据
    private int[][][] used= new int[9][9][];

    public Game()
    {
        shuduku = fromPuzzleString(initStr) ;
        calculateAllUseTiles();
    }
    //通过传来的坐标值，获取 该坐标 的 具体值（整数）
    private int getTile(int x , int y)
    {
        return shuduku[y*9+x] ;
    }
    public void calculateAllUseTiles(){
        for (int x=0;x<9;x++){
            for (int y=0;y<9;y++)
            {
                used[x][y]=calculateUsedTiles(x,y);
            }
        }
    }

    //取出某一个单元格已经不可以用的数据
    public int[] getUsedTilesByCoor(int x,int y){
        return  used[x][y];
    }
    public int[] calculateUsedTiles(int x,int y){
        int [] c= new int[9];
        //计算 该单元格中《列上不可用的数据
       for (int i=0;i<9;i++)
       {  if (i==y) continue;
        int t= getTile(x,i);
           if (t!=0) c[t-1]=t;
        }
        //计算 该单元格中《行上不可用的数据
        for (int i=0;i<9;i++)
        {  if (i==x) continue;
            int t= getTile(i,y);
            if (t!=0) c[t-1]=t;
        }
        //计算九宫格中不可用的数字
        int startx =(x/3)*3;
        int starty =(y/3)*3;
        for (int i =startx;i<startx+3;i++){
            for (int j=starty;j<starty+3;j++){
                if (i==x&&j==y) continue;
                int t=getTile(i,j);
                if (t!=0) c[t-1]=t;
            }
        }
        int nused =0;
        for (int t:c){
            if (t!=0) nused++;
        }
        int[]c1=new int[nused];
        nused=0;
        for (int t:c){
            if (t!=0){c1[nused++]=t;}
        }
        return c1;
    }

    public String getTileString(int x , int y)
    {
        int v = getTile(x , y);
        if(0 == v)
            return "" ;
        else

            return String.valueOf(v); //把获取的 整数 转成 字符串
    }
    //触摸手机屏的事件



    public int[] fromPuzzleString(String str)
    {
        int[] shudu = new int[str.length()] ;

        for(int i = 0 ; i < str.length() ; i++)
        {
            shudu[i] = str.charAt(i) - '0' ; //把获取的单个字符减去 '0' 转成整数，赋给 整形 shudu数组中
        }

        return shudu ;
    }
    public boolean setTileIfValid(int x , int y , int value)
    {
        // 取出某个单元格中 不可用的 数
        int tiles[] = getUsedTilesByCoor(x, y);

        if(value != 0)
        {
            for(int tile : tiles)
            {
                //如果用户选的数字和 “ 不可用数组” 里面的数字相同，则返回false，
                if(tile == value)
                {
                    return false ;
                }
            }
        }

        setTile(x,y,value) ;

        //每次修改之后都得 进行重新计算 不可用的值
        calculateAllUseTiles();

        return true;
    }
    protected int[] getUsedTile(int x,int y){return used[x][y];}
    //进行修改 数独数组 里的值
    private void setTile(int x , int y , int value)
    {
        shuduku[9*y+x] = value ;
    }
}
