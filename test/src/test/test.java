package test;

public class test {

	public static void main(String[] args) {
		try
		{
			String teachers[]={"刘海龙","孙传杰","孙悦"};
			for(int i=0; i<4;i++)
			{
				System.out.println(teachers[i]);
			}
		}catch(Exception e)
		{
			System.out.println("数组下标越界！");
			//return ;
			System.exit(0);
		}
		finally {
			System.out.println("显示完毕！");
		}
	}
}
