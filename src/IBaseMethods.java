import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public interface IBaseMethods<T> {
	public T copyFrom(T src);

	public T clone();

	@Override
	public String toString();

	public void readFrom(BufferedReader br) throws IOException;

	public void writeTo(PrintWriter pw);

	@Override
	public boolean equals(Object t);

	@Override
	public int hashCode();
}
