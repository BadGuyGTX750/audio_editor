package modifier_service;

public interface Modifier {
    public byte[] executeModifier(String command, byte[] file);
}
