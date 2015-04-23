import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Delegate {
	private Object object;
	private String method;
	
	public Delegate(Object object, String method) {
		this.object = object;
		this.method = method;
	}
	
	public void invoke(Object... args) {
		try {
			object.getClass().getMethod(method).invoke(object, args);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}

	public int hashCode() {
		return (object.toString() + method).hashCode();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Delegate))
			return false;
		if (obj == this)
			return true;

		if(hashCode() == obj.hashCode())
			return true;
		
		return false;
	}
}

public class EventManager {
	
	private static EventManager eventManager;
	
	public static EventManager getEventManager() {
		if(eventManager == null) {
			eventManager = new EventManager();
		}
		
		return eventManager;
	}
	
	public enum EventName {
	    UPDATE,
	    EXIT,
	}
	
	public Map<EventName, List<Delegate>> _Notify = new HashMap<EventName, List<Delegate>>();
	
	public void addEventNotify(EventName event, Object object, String method) {
		if(_Notify.get(event) == null)
			_Notify.put(event, new ArrayList<Delegate>());
		
		Delegate del = new Delegate(object, method);
		
		if(!_Notify.get(event).contains(del))
			_Notify.get(event).add(del);
	}
	
	public void removeEventNotify(EventName event, Object object, String method) {
		if(_Notify.get(event) == null)
			return;
		
		Delegate del = new Delegate(object, method);
		
		if(_Notify.get(event).contains(del))
			_Notify.get(event).remove(del);
	}
	
	public void notify(EventName event, Object... args) {
		if(_Notify.get(event) == null)
			return;
		
		List<Delegate> delegates = _Notify.get(event);
		
		for(Delegate del : delegates) {
			del.invoke(args);
		}

	}
}