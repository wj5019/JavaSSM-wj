package pojo;

//���ڲ���user�������������
public class User {
	//�����ʵ������������������
	public User() {
		System.out.println("user����Ĵ���");
	}
	
	//����ĳ�ʼ�����������ö��������
	public void init() {
		System.out.println("user����ĳ�ʼ��");
	}
	
	//������Զ��巽��
	public void sleep() {
		System.out.println("user�������Ϊ����");
	}
	
	//��������ٷ��������û�����ʹ��ʱ�����ж�������
	public void destroy() {
		System.out.println("user����������");
	}

}
