package pdl.backend;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.InputStream;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;
import pdl.imageprocessing.Blur;
import pdl.imageprocessing.Color;
import pdl.imageprocessing.Luminosity;

public class Algorithm {
    public static ResponseEntity<?> filter(InputStream image, Map<String, String> params) {
        // id?algorithm=X&p1=Y&p2=Z
        System.out.println(params.toString());
        if (params.containsKey("algorithm")) {
            Planar<GrayU8> planar = Converter.InputStreamToPlanar(image);
            Planar<GrayU8> copy = planar.clone();
            InputStream output;
            int value;
            switch (params.get("algorithm")) {
                case "blur":
                    // id?algorithm=blur&type=moyen|gaussien&value=X
                    if (!(params.containsKey("type") && params.containsKey("value"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    if (!(params.get("type").equalsIgnoreCase("moyen")
                            || params.get("type").equalsIgnoreCase("gaussien"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    if (!NumberUtils.isParsable(params.get("value"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }

                    if (params.get("type").equalsIgnoreCase("moyen")) {
                        value = NumberUtils.toInt(params.get("value"),0);
                        Blur.moyen(planar, copy, value);
                    } else {
                        value = NumberUtils.toInt(params.get("value"),0);
                        // Blur.gaussien(planar, copy);
                    }
                    output = Converter.PlanarToInputStream(copy);
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(output));
                case "color":
                    // id?algorithm=color&value=X
                    if (!(params.containsKey("value"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    if (!NumberUtils.isParsable(params.get("value"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }

                    value = NumberUtils.toInt(params.get("value"),0);
                    Color.color(planar, copy, value);
                    
                    output = Converter.PlanarToInputStream(copy);
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(output));
                case "histogramequalization":
                    // id?algorithm=histogramequalization&canal=S|V
                    if (!(params.containsKey("canal"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    if (!(params.get("canal").equalsIgnoreCase("S")
                            || params.get("canal").equalsIgnoreCase("V"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    output = Converter.PlanarToInputStream(copy);
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(output));
                case "luminosity":
                    // id?algorithm=luminosity&value=X
                    if (!(params.containsKey("value"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                    if (!NumberUtils.isParsable(params.get("value"))) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }

                    value = NumberUtils.toInt(params.get("value"),0);
                    Luminosity.luminosity(planar, copy, value);

                    output = Converter.PlanarToInputStream(copy);
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(output));
                case "outline":
                    // id?algorithm=outline

                    output = Converter.PlanarToInputStream(copy);
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(output));

                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
