package liangwen.sudu03;

import android.view.MotionEvent;

/**
 * Created by lenovo on 2015/7/25.
 */
public class Game {

    //��ʼ�� �Ź��������
    private final String initStr =
            "360000000004230800000004200"+
                    "070460003820000014500013020"+
                    "001900000007048300000000045";

    //����һ�� ���� ��� ��ʼ�����ݣ����� Ҫ�� initStr��������ݷ��뿪�������������
    private int[] shuduku = new int[9*9] ;
    //��Ԫ��ʹ�õ�����
    private int[][][] used= new int[9][9][];

    public Game()
    {
        shuduku = fromPuzzleString(initStr) ;
        calculateAllUseTiles();
    }
    //ͨ������������ֵ����ȡ ������ �� ����ֵ��������
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

    //ȡ��ĳһ����Ԫ���Ѿ��������õ�����
    public int[] getUsedTilesByCoor(int x,int y){
        return  used[x][y];
    }
    public int[] calculateUsedTiles(int x,int y){
        int [] c= new int[9];
        //���� �õ�Ԫ���С����ϲ����õ�����
       for (int i=0;i<9;i++)
       {  if (i==y) continue;
        int t= getTile(x,i);
           if (t!=0) c[t-1]=t;
        }
        //���� �õ�Ԫ���С����ϲ����õ�����
        for (int i=0;i<9;i++)
        {  if (i==x) continue;
            int t= getTile(i,y);
            if (t!=0) c[t-1]=t;
        }
        //����Ź����в����õ�����
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

            return String.valueOf(v); //�ѻ�ȡ�� ���� ת�� �ַ���
    }
    //�����ֻ������¼�



    public int[] fromPuzzleString(String str)
    {
        int[] shudu = new int[str.length()] ;

        for(int i = 0 ; i < str.length() ; i++)
        {
            shudu[i] = str.charAt(i) - '0' ; //�ѻ�ȡ�ĵ����ַ���ȥ '0' ת������������ ���� shudu������
        }

        return shudu ;
    }
    public boolean setTileIfValid(int x , int y , int value)
    {
        // ȡ��ĳ����Ԫ���� �����õ� ��
        int tiles[] = getUsedTilesByCoor(x, y);

        if(value != 0)
        {
            for(int tile : tiles)
            {
                //����û�ѡ�����ֺ� �� ���������顱 �����������ͬ���򷵻�false��
                if(tile == value)
                {
                    return false ;
                }
            }
        }

        setTile(x,y,value) ;

        //ÿ���޸�֮�󶼵� �������¼��� �����õ�ֵ
        calculateAllUseTiles();

        return true;
    }
    protected int[] getUsedTile(int x,int y){return used[x][y];}
    //�����޸� �������� ���ֵ
    private void setTile(int x , int y , int value)
    {
        shuduku[9*y+x] = value ;
    }
}
