package dev.redicloud.service.version;

import dev.redicloud.service.version.handler.download.PaperDownloadHandler;
import dev.redicloud.service.version.handler.download.VelocityDownloadHandler;
import dev.redicloud.service.version.handler.download.WaterfallDownloadHandler;
import io.javalin.Javalin;
import io.javalin.http.HandlerType;

public class RestServer {

    private static String[] arguments;

    public static void main(String[] args) {
        arguments = args;
        Javalin web = Javalin.create().start(getPort());
        web.addHandler(HandlerType.GET, "/paper/{version}/{build}/download", new PaperDownloadHandler());
        web.addHandler(HandlerType.GET, "/waterfall/{version}/{build}/download", new WaterfallDownloadHandler());
        web.addHandler(HandlerType.GET, "/velocity/{version}/{build}/download", new VelocityDownloadHandler());
    }

    public static int getPort(){
        for (String argument : arguments) {
            if(argument.startsWith("--port=")){
                return Integer.parseInt(argument.substring(7));
            }
        }
        return 25565;
    }

}