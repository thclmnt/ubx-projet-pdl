package pdl.backend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class ImageDao implements Dao<Image> {

  private final Map<Long, Image> images = new HashMap<>();

  public ImageDao() throws IOException {
    Path path = Paths.get("images");
    if (!Files.exists(path)) {
      throw new IOException("'images' folder not found");
    };

    Files.find(path, 999, (p, bfa) -> bfa.isRegularFile()).forEach((Path imgPath) -> {
      byte[] fileContent;
      try {
        String contentType = Files.probeContentType(imgPath);
        if (contentType.equalsIgnoreCase("image/jpeg") || contentType.equalsIgnoreCase("image/png")) {
          fileContent = Files.readAllBytes(imgPath);
          Image img = new Image(imgPath.toString(), fileContent);
          images.put(img.getId(), img);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    ;

  }

  @Override
  public Optional<Image> retrieve(final long id) {
    return Optional.ofNullable(images.get(id));
  }

  @Override
  public List<Image> retrieveAll() {
    return new ArrayList<Image>(images.values());
  }

  @Override
  public void create(final Image img) {
    images.put(img.getId(), img);
  }

  @Override
  public void update(final Image img, final String[] params) {
    img.setName(Objects.requireNonNull(params[0], "Name cannot be null"));

    images.put(img.getId(), img);
  }

  @Override
  public void delete(final Image img) {
    images.remove(img.getId());
  }
}
