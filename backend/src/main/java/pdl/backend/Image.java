package pdl.backend;

public class Image {
  private static Long count = Long.valueOf(0);
  private Long id;
  private String name;
  private byte[] data;
  private String size;
  private String type;

  public Image(final String name, final byte[] data, final String size, final String type) {
    id = count++;
    this.name = name;
    this.data = data;
    this.size = size;
    this.type = type;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSize() {
    return size;
  }

  public String getType() {
    return type;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public byte[] getData() {
    return data;
  }

}
