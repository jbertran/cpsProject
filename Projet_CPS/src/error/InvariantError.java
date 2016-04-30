package error;
@SuppressWarnings("serial")
public class InvariantError extends Error {

	public InvariantError(String err){
		super(err);
	}
}