package school.exercise.geotrack;

import java.util.HashMap;
import java.util.Map;

public class SingletonManager {

	static protected Map<Class<?>, Object> singletones = new HashMap<Class<?>, Object>();;
	
	static public Object getSingleton(Class<?> cls) {
		return singletones.get(cls);		
	}	
	
	static public void registerSingleton(Object instance, Class<?> cls) throws Exception {
		if (singletones.containsKey(cls)) {			
			throw new Exception("Cannot create more than one sigleton object per type.");
		}
		else {
			singletones.put(cls, instance);
		}
	}
	
	static public void unRegisterSingleton(Class<?> cls) throws Exception {
		if (!singletones.containsKey(cls)) {			
			throw new Exception("Cannot unregister singleton bacause that class is never registered.");
		}
		else
		{
			singletones.remove(cls);
		}
	}
	
}
