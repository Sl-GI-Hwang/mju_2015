
public class Professor {
	private String name;
	private Lecture lecture;
	public Professor(){
		
	}
	public Professor(String name){
		this.name = name;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public void addLecture(Lecture lecture){
		// TODO Auto-generated method stub
		this.lecture = lecture;
	}
	public void showLecture() {
		// TODO Auto-generated method stub
		System.out.println("강의명:"+lecture.getName());
		System.out.println("학생:"+lecture.getStudent().getName());
	}
}
