import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class FixedSizedQueue<T> implements Iterable<T>
    {
        Queue<T> q = new LinkedList<T>();

        public int limit;
        
        public void add(T obj)
        {
            q.add(obj);
            while (q.size() > limit)
            	q.poll();
        }

		public Iterator<T> iterator() {
			return q.iterator();
		}
    }