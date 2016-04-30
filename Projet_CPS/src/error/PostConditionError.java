package error;
@SuppressWarnings("serial")
public class PostConditionError extends Error{
	
	public PostConditionError(String err) {
		super(err);
	}
}