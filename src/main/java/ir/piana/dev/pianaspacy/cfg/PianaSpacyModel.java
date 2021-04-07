package ir.piana.dev.pianaspacy.cfg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties("piana.spacy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PianaSpacyModel {
    private int port;
    private Map<String, String> routes;
}
