package audio;

import java.util.ArrayList;

public class AudioManager {

	private ArrayList<Audio> sounds = new ArrayList<Audio>();
	private ArrayList<Audio> toRemove = new ArrayList<Audio>();
	
	private float ambientVolume = 0.25f;
	private float effectVolume = 1.0f;
	
	public void tick() {
		for(Audio s : sounds)
			s.tick();
		sounds.removeAll(toRemove);
	}

	public ArrayList<Audio> getSounds(){
		return sounds;
	}
	
	public void stopAmbient() {
		for(Audio s : sounds) {
			if(s.isAmbient()) {
				s.stopAmbience();
			}
		}
	}
	
	public void startAmbient() {
		for(Audio s : sounds) {
			if(s.isAmbient()) {
				s.startAmbience();
			}
		}
	}
	
	public void remove(Audio a) {
		toRemove.add(a);
	}

	public float getAmbientVolume() {
		return ambientVolume;
	}

	public void setAmbientVolume(float ambientVolume) {
		this.ambientVolume = ambientVolume;
	}

	public float getEffectVolume() {
		return effectVolume;
	}

	public void setEffectVolume(float effectVolume) {
		this.effectVolume = effectVolume;
	}
	
}
