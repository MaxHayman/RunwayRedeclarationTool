import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class EventManager {
	
	public enum EventName {
	    UPDATE_DISPLAY,
	}
	
	public Map<EventName, Map<Object, Method>> _Notify = new HashMap<EventName, Map<Object, Method>>();
	
	public void AddEventNotify(EventName event, Method method, Object o) {
		if(_Notify.get(event) == null)
			_Notify.put(event, new HashMap<Object, Method>());
		
		_Notify.get(event).put(o, method);
	}
	
	public void Notify(EventName event, Object... arg) {
		if(_Notify.get(event) == null)
			return;
		
		Map<Object, Method> methods = _Notify.get(event);
		
		Iterator<Entry<Object, Method>> it = methods.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<Object, Method> pair = (Map.Entry<Object, Method>)it.next();
	        try {
				pair.getValue().invoke(pair.getKey(), arg);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				System.out.println("Invalid amount of arguments for " + event + " " + pair.getValue() + " " + pair.getKey());
				e.printStackTrace();
			}
	    }

	}
}
