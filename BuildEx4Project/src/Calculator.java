import java.util.Scanner;
import java.util.Stack;

public class Calculator {
	public int sum(int num1, int num2){
		return num1 + num2;
	}

	public int minus(int num1, int num2){
		return num1-num2;
	}
	
	public static void main(String[] args) {
		System.out.println("Please input num1:");
		Scanner num =new Scanner (System.in);
		int num1=num.nextInt();
			
	 
		System.out.println("Please input num2:");
		num=new Scanner (System.in);
		int num2=num.nextInt();
		 
		
		Calculator cal = new Calculator();
		
		System.out.println("Calculator result is :"+ cal.sum(num1, num2));
		num.close();

		System.out.println("End.");
		return;		
	}
	
	
}
/*
	
	public static void main(String[] args) {
		System.out.println("Please input num1:");
		Scanner num =new Scanner (System.in);
		int num1=num.nextInt();
		
		System.out.println("Please input num2:");
		num=new Scanner (System.in);
		int num2=num.nextInt();
		
		int number=num.nextInt();
		
		System.out.println("Calculator result is :"+ sum(num1, num2));
		
		int number=num.nextInt();
		for(int i=0;i<number;i++){
			System.out.print("Please input a case:");
			Scanner in = new Scanner(System.in);
			String input = new String();
			input=in.nextLine();	
			if(check(input)){	
				String[] expression=fun(input);
				System.out.println("The result is "+calculate(expression));
				continue;
			}
			else{	
				System.out.println("Error input!");				
				continue;
			}
		}
		num.close();
		return;
	}
	
	private static boolean check(String input){
		if(input.length()==0)	
			return false;
		for(int i=0; i<input.length();i++){
			if(type(input.charAt(i))==-1)	
				return false;
		}
		int left=0, right=0;
		for(int i=0; i<input.length();i++){
			if(input.charAt(i)=='(')	left++;
			else if(input.charAt(i)==')')	right++;
			if(left<right)	return false; 
		}
		if(left!=right)	return false;
		
		String[] exp=fun(input);
		int operator=0, num=0;
		for(int i=0; i<exp.length; i++){	
			if(exp[i].equals("+") || exp[i].equals("-") || exp[i].equals("*") || exp[i].equals("/") || exp[i].equals("%"))
				operator++;	
			else
				num++;	
		}
		if(num!=operator+1)	
			return false;
			
		return true;
	}
	
	private static String[] fun(String input){	
		Stack<Character> s=new Stack<Character>();	
		char[] inputchar = input.toCharArray();
		int length=inputchar.length;
		char[] infix_exp=new char[length+1];	
		for(int i=0; i<length; i++){
			infix_exp[i]=inputchar[i];
		}
		infix_exp[length]='\0';
		
		String[] postfix=new String[100];	
		char[] temp=new char[100];	
		char x, y;
		int i=0, j=0;
		
		x = infix_exp[i];
		while(x!='\0'){
			if(type(x)==0){		
				int k=0;
				while(type(x)==0){
					temp[k++]=x;
					x=infix_exp[++i];
				}
				i--;
				temp[k]='\0';
				char[] temp2=new char [k];
				int p=0;
				for(p=0;p<k;p++)	temp2[p]=temp[p];
				postfix[j++]=new String(temp2);
				
			}
			else if(type(x)==1 || type(x)==2){	
				if(s.empty()==true)
					s.push(new Character(x));
				else {
					for(y=s.pop().charValue();type(y)>=type(x)&&y!='(';y=s.pop().charValue()){
						postfix[j++]=Character.toString(y);
						if(s.empty()==true)
							break;
					}
					if(type(y) < type(x) || y=='(')
						s.push(new Character(y));
					s.push(new Character(x));
				}
			}
			else if(type(x)==3){	
				s.push(new Character(x));
			}
			else if(type(x)==4){
				for(y=s.pop().charValue();y!='(';y=s.pop().charValue())
					postfix[j++]=Character.toString(y);
			}
			else{
				break;
			}	
			x=infix_exp[++i];
		}
		
		if(s.empty()!=true){	
		for(y=s.pop().charValue(); s.empty()!=true;y=s.pop().charValue())
			postfix[j++]=Character.toString(y);
		postfix[j++]=Character.toString(y);
		}
		
		String[] postfix_exp=new String[j];
		for(int k=0; k<j; k++)
			postfix_exp[k]=postfix[k];
				
		return postfix_exp;
	}
	
	private static int calculate(String[] postfix_exp){	
		int result;
		int temp1, temp2;
		Stack<Integer> s=new Stack<Integer>();
		for(int i=0; i<postfix_exp.length; i++){
			if(postfix_exp[i].equals("+")){
				temp1=s.pop().intValue();
				temp2=s.pop().intValue();
				result=temp2+temp1;
				s.push(new Integer(result));
			}
			else if(postfix_exp[i].equals("-")){
				temp1=s.pop().intValue();
				temp2=s.pop().intValue();
				result=temp2-temp1;
				s.push(new Integer(result));
			}
			else if(postfix_exp[i].equals("*")){
				temp1=s.pop().intValue();
				temp2=s.pop().intValue();
				result=temp2*temp1;
				s.push(new Integer(result));
			}
			else if(postfix_exp[i].equals("/")){
				temp1=s.pop().intValue();
				temp2=s.pop().intValue();
				result=temp2/temp1;
				s.push(new Integer(result));
			}
			else if(postfix_exp[i].equals("%")){
				temp1=s.pop().intValue();
				temp2=s.pop().intValue();
				result=temp2%temp1;
				s.push(new Integer(result));
			}
			else {
				result=Integer.parseInt(postfix_exp[i]);
				s.push(new Integer(result));
			}
		}
		result=s.pop();
		return result;
	}
	
	private static int type(char a){	
		if(a>='0'&&a<='9')
			return 0;	
		if(a=='+' || a=='-')
			return 1;	
		if(a=='*' || a=='/' || a=='%')
			return 2;	
		if(a=='(')
			return 3;
		if(a==')')
			return 4;
		return -1;
	}
	
}
*/