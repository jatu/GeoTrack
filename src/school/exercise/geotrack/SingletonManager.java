package school.exercise.geotrack;

public interface SingletonManager {
	public Object getSingleton(Class<?> type);
	public void registerSingleton(Object instance, Class<?> type) throws Exception;
	public void unRegisterSingleton(Class<?> type) throws Exception;
}
