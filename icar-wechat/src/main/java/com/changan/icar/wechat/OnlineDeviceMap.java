package com.changan.icar.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.po.Device;
import com.changan.icar.comm.po.Location;
import com.changan.icar.wechat.service.DeviceService;

@Component
public class OnlineDeviceMap {

	@Autowired
	private DeviceService deviceService;

	private HashMap<String, OnlineDevice> onlineMap;

	private static Logger logger = Logger.getLogger(OnlineDeviceMap.class);

	private static Integer offlineMills = 40 * 60;

	public OnlineDeviceMap() {
		onlineMap = new HashMap<>();
		new Thread() {
			public void run() {
				while (true) {
					Set<String> keySet = onlineMap.keySet();
					Iterator<String> iterator = keySet.iterator();
					while (iterator.hasNext()) {
						String key = iterator.next();
						Long gap = new Date().getTime() - onlineMap.get(key).getLastUpdate().getTime();
						if (gap > offlineMills * 1000) {
							iterator.remove();
							onlineMap.remove(key);
							System.out.println("设备已离线，tuid:" + key);
							logger.info("设备已离线，tuid:" + key);
							Device device = new Device();
							device.setStatus(IcarConst.DEVICE_STATUS_OFFLINE);
							deviceService.updateDeviceByTuid(key, device);
						}
					}
					// 40秒检查一次
					try {
						Thread.sleep(offlineMills * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		}.start();

	}

	public OnlineDevice getOnlineDeviceByTuid(String tuid) {
		return onlineMap.get(tuid);
	}

	public boolean addOnlineDeviceByTuid(String tuid, Location location) {
		try {
			logger.info("设备上线,tuid:" + tuid);
			System.out.println("设备上线,tuid:" + tuid);
			OnlineDevice onlineDevice = new OnlineDevice();
			onlineDevice.setTuid(tuid);
			onlineDevice.setOnline(true);
			// onlineDevice.setLocation(location);
			onlineDevice.setLastUpdate(new Date());

			Device device = new Device();
			device.setStatus(IcarConst.DEVICE_STATUS_ONLINE);
			deviceService.updateDeviceByTuid(tuid, device);
			// 生成travel的起点
			// Travel travel = new Travel();
			// travel.setTuid(tuid);
			// travel.setUuid(StringUtils.generateUuid());
			// travelService.addTravelByTuid(tuid, travel);
			//
			// onlineDevice.setTravelUuid(travel.getUuid());
			onlineMap.put(tuid, onlineDevice);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 更新最近时间
	 * 
	 * @param tuid
	 */
	public void updateOnlineDeviceByTuid(String tuid) {

		OnlineDevice device = getOnlineDeviceByTuid(tuid);
		device.setLastUpdate(new Date());
	}

}
