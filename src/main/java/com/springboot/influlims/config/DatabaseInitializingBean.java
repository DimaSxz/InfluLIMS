package com.springboot.influlims.config;

import com.springboot.influlims.dao.RegionDao;
import com.springboot.influlims.dao.RoleDao;
import com.springboot.influlims.dao.UserDao;
import com.springboot.influlims.entity.RegionEntity;
import com.springboot.influlims.entity.RoleEntity;
import com.springboot.influlims.entity.UserEntity;
import com.springboot.influlims.entity.UserRoleEntity;
import com.springboot.influlims.entity.enums.Role;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializingBean implements InitializingBean {

	@Autowired
	RoleDao roleDao;

	@Autowired
	RegionDao regionDao;

	@Autowired
	UserDao userDao;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private void setRegions() {
		String regionNames[] = new String[]{"Республика Адыгея", "Республика Башкортостан", "Республика Бурятия", "Республика Алтай", "Республика Дагестан", "Республика Ингушетия", "Кабардино-Балкарская Республика", "Республика Калмыкия", "Республика Карачаево - Черкесия", "Республика Карелия", "Республика Коми", "Республика Марий Эл", "Республика Мордовия", "Республика Саха(Якутия)", "Республика Северная Осетия - Алания","Республика Татарстан", "Республика Тыва", "Удмуртская Республика", "Республика Хакасия", "Чеченская республика", "Чувашская Республика", "Алтайский край", "Краснодарский край", "Красноярский край", "Приморский край", "Ставропольский край", "Хабаровский край", "Амурская область", "Архангельская область", "Астраханская область", "Белгородская область", "Брянская область", "Владимирская область", "Волгоградская область", "Вологодская область", "Воронежская область", "Ивановская область", "Иркутская область", "Калининградская область", "Калужская область", "Камчатский край", "Кемеровская область", "Кировская область", "Костромская область", "Курганская область", "Курская область", "Ленинградская область", "Липецкая область", "Магаданская область", "Московская область", "Мурманская область", "Нижегородская область", "Новгородская область", "Новосибирская область", "Омская область", "Оренбургская область", "Орловская область", "Пензенская область", "Пермский край", "Псковская область", "Ростовская область", "Рязанская область", "Самарская область", "Саратовская область", "Сахалинская область", "Свердловская область", "Смоленская область", "Тамбовская область", "Тверская область", "Томская область", "Тульская область", "Тюменская область", "Ульяновская область", "Челябинская область", "Забайкальский край", "Ярославская область", "Москва", "Санкт-Петербург", "Еврейская автономная область", "Ненецкий автономный округ", "Ханты-Мансийский автономный округ - Югра", "Чукотский автономный округ", "Ямало-Ненецкий автономный округ", "Республика Крым", "Севастополь", "Чеченская Республика"};
		for(int i = 0; i < regionNames.length; i++) {
			if(regionDao.findByRegionUNID(i + 1) == null)
				regionDao.save(new RegionEntity(i + 1, regionNames[i]));
		}
	}

	private void setRoles() {
		for(Role role : Role.values()) {
			if(roleDao.findByName(role) == null) {
				RoleEntity roleEntity = new RoleEntity(role);
				roleDao.save(roleEntity);
				UserEntity userEntity = new UserEntity(role.name(), bCryptPasswordEncoder.encode("_@#%&*^$@_" + role.name()), true);
				userEntity.getUserRolesEntities().add(new UserRoleEntity(userEntity, roleEntity));
				userDao.save(userEntity);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRoles();
		setRegions();
	}
}
