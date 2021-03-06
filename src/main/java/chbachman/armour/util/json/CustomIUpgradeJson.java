package chbachman.armour.util.json;

import java.lang.reflect.Type;

import chbachman.api.registry.UpgradeRegistry;
import chbachman.api.upgrade.IUpgrade;

import com.google.gson.*;

public class CustomIUpgradeJson implements JsonDeserializer<IUpgrade>, JsonSerializer<IUpgrade>{

	@Override
	public JsonElement serialize(IUpgrade src, Type typeOfSrc, JsonSerializationContext context){
		return new JsonPrimitive(src.getBaseName());
	}

	@Override
	public IUpgrade deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException{
		return UpgradeRegistry.getUpgrade(json.getAsString());
	}

}
