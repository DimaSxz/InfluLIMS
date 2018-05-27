package com.springboot.influlims.config;

import com.springboot.influlims.dao.*;
import com.springboot.influlims.entity.*;
import com.springboot.influlims.entity.enums.Gender;
import com.springboot.influlims.entity.enums.Role;
import com.springboot.influlims.entity.enums.Vaccine;
import com.springboot.influlims.service.Helper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

@Component
public class DatabaseInitializingBean implements InitializingBean {

//	private static final int N = 0;

	@Autowired
	RoleDao roleDao;

	@Autowired
	RegionDao regionDao;

	@Autowired
	UserDao userDao;

	@Autowired
	ProjectDao projectDao;

	@Autowired
	ProviderDao providerDao;

	@Autowired
	ProviderProjectDao providerProjectDao;

	@Autowired
	ReagentTypeDao reagentTypeDao;

	@Autowired
	ReagentDao reagentDao;

	@Autowired
	PatientDao patientDao;

	@Autowired
	SampleDao sampleDao;

	@Autowired
	Helper helper;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private ProjectEntity setProject(UserEntity userEntity) {
		ProjectEntity projectEntity = new ProjectEntity(new Random().nextInt() + " project " + System.currentTimeMillis(), userEntity);
		return projectDao.save(projectEntity);
	}

	private ProviderEntity setProvider(UserEntity userEntity, RegionEntity regionEntity) {
		ProviderEntity providerEntity = new ProviderEntity(new Random().nextInt() + " provider " + System.currentTimeMillis(), userEntity, regionEntity);
		return providerDao.save(providerEntity);
	}

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
				UserEntity userEntity = new UserEntity(role.name(), bCryptPasswordEncoder.encode(role.name()), true);
//				UserEntity userEntity = new UserEntity(role.name(), bCryptPasswordEncoder.encode("_@#%&*^$@_" + role.name()), true);
				userEntity.getUserRolesEntities().add(new UserRoleEntity(userEntity, roleEntity));
				userDao.save(userEntity);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRoles();
		setRegions();
		UserEntity userEntity = userDao.findByLogin(Role.ROLE_ADMIN.name());
		if(providerProjectDao.count() == 0 || patientDao.count() == 0 || sampleDao.count() == 0) {
			RegionEntity regionEntity = regionDao.findByRegionUNID(78);
			ProjectEntity projectEntity = setProject(userEntity);
			ProviderEntity providerEntity = setProvider(userEntity, regionEntity);
			PatientEntity patientEntity = patientDao.save(new PatientEntity(Vaccine.X, (short) new Random().nextInt(Short.MAX_VALUE / 100), Gender.X, userEntity, regionEntity));
			ProviderProjectEntity providerProject = providerProjectDao.save(new ProviderProjectEntity(userEntity, providerEntity, projectEntity));
			sampleDao.save(new SampleEntity(userEntity, patientEntity, providerProject, String.valueOf(new Random().nextLong()), new Random().nextBoolean(), "testType", new Random().nextInt(Short.MAX_VALUE / 100), new Date(), new Date(), new Date(), helper.getSeason()));
			sampleDao.save(new SampleEntity(userEntity, patientEntity, providerProject, String.valueOf(new Random().nextLong()), new Random().nextBoolean(), "testType2", new Random().nextInt(Short.MAX_VALUE / 100), new Date(), new Date(), new Date(), helper.getSeason()));
		}
		if(reagentDao.count() == 0 || reagentTypeDao.count() == 0) {
			Collection<ReagentTypeEntity> reagentTypeEntities = new HashSet<>();
			ReagentTypeEntity ext = new ReagentTypeEntity("Реагент для экстракции", userEntity);
			ReagentTypeEntity pcr = new ReagentTypeEntity("Регент для ПЦР", "Тестовые каналы ПЦР", "Каналы поиска ПЦР", userEntity);
			reagentTypeEntities.add(ext);
			reagentTypeEntities.add(pcr);
			reagentTypeDao.saveAll(reagentTypeEntities);

			Collection<ReagentEntity> reagentEntities = new HashSet<>();
			reagentEntities.add(new ReagentEntity(userEntity, ext, "#" + System.currentTimeMillis(), 150L));
			reagentEntities.add(new ReagentEntity(userEntity, ext, "#" + System.currentTimeMillis(), 150L));
			reagentEntities.add(new ReagentEntity(userEntity, pcr, "#" + System.currentTimeMillis(), 150L));
			reagentEntities.add(new ReagentEntity(userEntity, pcr, "#" + System.currentTimeMillis(), 150L));
			reagentDao.saveAll(reagentEntities);
		}
	}
}
