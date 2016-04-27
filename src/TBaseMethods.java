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
	public String toString(){
		throw new UnsupportedOperationException();
	}

	public abstract void readFrom(BufferedReader br) throws IOException;

	public abstract void writeTo(PrintWriter pw);

	@Override
	public boolean equals(Object t){
		throw new UnsupportedOperationException();
	}

	@Override
	public int hashCode(){
		throw new UnsupportedOperationException();
	}
}
