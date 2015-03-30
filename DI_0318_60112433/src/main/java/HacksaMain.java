import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class HacksaMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	//	Professor professor = new Professor("choi");
	//	Lecture lecture1 = new Lecture("소프트웨어 공학");
	//	Student student = new Student("Hwang Sl Gi");
		
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				new String[]{"applicationContext.xml"});
		
		Professor professor = (Professor)factory.getBean("professor");
		Lecture Lecture2 = (Lecture)factory.getBean("Lecture2");
		Student student = (Student)factory.getBean("student");
		Student student2 = (Student)factory.getBean("student2");
		
		professor.addLecture(Lecture2);
		Lecture2.addStudent(student2);
		professor.showLecture();
		
	}

}
