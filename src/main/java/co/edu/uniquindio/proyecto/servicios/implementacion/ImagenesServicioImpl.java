package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.servicios.interfaces.ImagenesServicio;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImagenesServicioImpl  {

   /** private final Cloudinary cloudinary;
    public ImagenesServicioImpl(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dsia0fezi");
        config.put("api_key", "279487583872753");
        config.put("api_secret", "***************************");
        cloudinary = new Cloudinary(config);
    }
    @Override
    public Map subirImagen(MultipartFile file) throws Exception{
        File file = convertir(imagen);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder",
                String.format("uniquindio/proyecto/fotos", carpeta)));
    }
    @Override
    public Map eliminarImagen(String idImagen) throws Exception{
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }
    private File convertir(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }**/
}
