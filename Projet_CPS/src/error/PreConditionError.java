package error;
@SuppressWarnings("serial")
public class PreConditionError extends Error{
	
	public PreConditionError(String err) {
		super(err);
	}
}