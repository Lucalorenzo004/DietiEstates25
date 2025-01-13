package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.AddonsDTO;

public interface AddonsService {
    AddonsDTO saveAddons(AddonsDTO addonsDTO);
    AddonsDTO editAddons(AddonsDTO addonsDTO);
    boolean deleteAddons(long addonsId);
}
