package nkg5.serverswither;

import java.util.HashMap;

public class Config {

	private HashMap<String, String> servers;
	private HashMap<String, String> props;

	public void setServers(HashMap<String, String> servers){
		this.servers = servers;
	}
	public HashMap<String, String> getServers(){
		return servers;
	}
	public void setProps(HashMap<String, String> props){
		this.props = props;
	}
	public HashMap<String, String> getProps(){
		return props;
	}
	public void setProp(String key, String value){
		props.put(key, value);
	}
	public String getProp(String key){
		return props.get(key);
	}
	public String getServer(String key){
		return servers.get(key);
	}
	public void setServer(String key, String value){
		servers.put(key, value);
	}
}
