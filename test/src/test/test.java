package test;

public class test {

	public static void main(String[] args) {
		try
		{
			String teachers[]={"������","�ﴫ��","����"};
			for(int i=0; i<4;i++)
			{
				System.out.println(teachers[i]);
			}
		}catch(Exception e)
		{
			System.out.println("�����±�Խ�磡");
			//return ;
			System.exit(0);
		}
		finally {
			System.out.println("��ʾ��ϣ�");
		}
	}
}