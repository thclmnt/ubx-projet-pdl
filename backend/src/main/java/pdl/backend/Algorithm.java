package pdl.backend;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;

public class Algorithm {
    public static ResponseEntity<?> filter(Planar<GrayU8> image, Map<String, String> params) {
        // id?algorithm=X&p1=Y&p2=Z
        if (params.containsKey("algorithm")) {
            switch (params.get("algorithm")) {
                case "blur":
                    // id?algorithm=blur&type=moyen|gaussien&value=X
                    if (!(params.containsKey("type") && params.containsKey("value"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    break;
                case "color":
                    // id?algorithm=color&value=X
                    if (!(params.containsKey("value"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    break;
                case "histogramequalization":
                    // id?algorithm=histogramequalization&canal=S|V
                    if (!(params.containsKey("canal"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    break;
                case "luminosity":
                    // id?algorithm=luminosity&value=X
                    if (!(params.containsKey("value"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    break;
                case "outline":
                    // id?algorithm=outline

                    break;

                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
