package interfaces;

public interface IPersistenceHandler {
    public void serialize(IEntityHandler handler, String file);
    public void deserialize(IEntityHandler handler, String file);
}
