package IntRoom.Introom_backend.utils;

import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class LocalityValidator {

    private static final Set<String> VALID_LOCALITIES = Set.of(
            "BRS Nagar",
            "Haibowal",
            "Model Town",
            "Sarabha Nagar",
            "Civil Lines",
            "Dugri",
            "PAU",
            "GNE College Area",
            "PCTE Area",
            "SCD College Area",
            "Khalsa College Area",
            "Gill Road",
            "Pakhowal Road",
            "Ferozepur Road",
            "Rajguru Nagar",
            "Shimlapuri",
            "Lohara",
            "Basant Avenue",
            "Shaheed Bhagat Singh Nagar",
            "Guru Arjan Dev Nagar",
            "Jawaddi",
            "Urban Estate Phase 1",
            "Urban Estate Phase 2"
    );

    public static boolean isValid(String locality) {
        return VALID_LOCALITIES.contains(locality);
    }

    public static Set<String> getAllValidLocalities() {
        return VALID_LOCALITIES;
    }

}
