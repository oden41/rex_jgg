import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * はじめはインターフェイスにしていたが，clone,equalsメソッドなどを考慮して変更
 * @author Shumpmita
 *
 */
public abstract class TBaseMethods<T> {
	public abstract T copyFrom(T src);

	@Override
	public abstract T clone();

	@Override
	public abstract String toString();

	public abstract void readFrom(BufferedReader br) throws IOException;

	public abstract void writeTo(PrintWriter pw);

	@Override
	public abstract boolean equals(Object t);

	@Override
	public abstract int hashCode();
}
