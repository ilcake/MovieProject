package movie;

public class Year {

	Object link;
	Object content;
	public Year(Object link, Object content) {
		super();
		this.link = link;
		this.content = content;
	}
	public Object getLink() {
		return link;
	}
	public void setLink(Object link) {
		this.link = link;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "" + content;
	}
	
	
}
