package test;

public class test{
	Object[] value;
	int len;
	public test(){
		value =new Object[16];
	}
	public test(int len){
		value =new Object[len];
	}
	public int length(){
		return len;
	}
	public void add(Object e){
		value[len] = e;
		len++;
		if(len >= value.length){
			int newlen = value.length*2;
			Object[] newvalue = new Object[newlen];
			//System.arraycopy(src, srcPos, dest, destPos, length);
			for(int i = 0 ; i < value.length ; i++){
				newvalue[i] = value[i];
			}
			value = newvalue;
		}
	}
	public Object get(int index){
		if(index>len-1||index<0){
			try{
				throw new Exception();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return value[index];
	}
	public static void main(String[] args){
		test list = new test();
		System.out.println(list.length());
		list.add("aaa");
		list.add('c');
		//for(int i=0;i<list.length();i++)
		//System.out.println(list.get(i));
	}	
}
