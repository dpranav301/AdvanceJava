import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustBasicDetailsServiceImpl implements CustBasicDetailsService {

    @Override
    public CustBasicDetailsDTO getCustBasicDetails(String applicationRefNo) {
        // Parse JSON and extract data using ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Assuming "jsonResponse" contains the JSON response you provided
            ResponseData responseData = objectMapper.readValue(jsonResponse, ResponseData.class);
            
            // Extract CustBasicDetails
            List<CustBasicDetails> custBasicDetailsList = responseData.getData().getRespData().getCustBasicDetails();
            for (CustBasicDetails custBasicDetails : custBasicDetailsList) {
                if (applicationRefNo.equals(custBasicDetails.getApplicationRefNo())) {
                    CustBasicDetailsDTO dto = new CustBasicDetailsDTO();
                    dto.setApplicantId(custBasicDetails.getApplicantId());
                    dto.setName(custBasicDetails.getName());
                    dto.setDob(custBasicDetails.getDob());
                    dto.setPAN(custBasicDetails.getPAN());
                    dto.setFatherName(custBasicDetails.getFatherName());
                    return dto;
                }
            }
        } catch (Exception e) {
            // Handle exceptions appropriately
        }
        return null;
    }
}



import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustAddressDetailsService {

    @Autowired
    private ObjectMapper objectMapper; // Inject an instance of ObjectMapper

    public CustAddressDetailsMailingDTO getCustAddressDetailsMailing(String jsonResponse) {
        try {
            ResponseData responseData = objectMapper.readValue(jsonResponse, ResponseData.class);
            Data.RespData.CustAddressDetails custAddressDetailsMailing = responseData.getData().getRespData().getCustAddressDetails_MAILING();
            
            if (custAddressDetailsMailing != null) {
                CustAddressDetailsMailingDTO dto = new CustAddressDetailsMailingDTO();
                dto.setId(custAddressDetailsMailing.getId());
                dto.setApplicantId(custAddressDetailsMailing.getApplicant_id());
                dto.setAddressType(custAddressDetailsMailing.getAddress_type());
                dto.setAddress(custAddressDetailsMailing.getAddress());
                dto.setDistCode(custAddressDetailsMailing.getDist_code());
                dto.setStateCode(custAddressDetailsMailing.getState_code());
                dto.setPinCode(custAddressDetailsMailing.getPin_code());

                return dto;
            }
        } catch (Exception e) {
            // Handle exceptions appropriately
        }
        return null;
    }
}
