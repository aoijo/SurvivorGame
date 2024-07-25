package UseCase.World;

import Entity.World.Resource;
import Utils.ReadCSV;

public class ResourceUseCase {
    private String[][] ResourceData;


    public ResourceUseCase() {
        loadResourceData();
    }

    private void loadResourceData() {
        this.ResourceData = ReadCSV.read("Data/Resource.csv");
    }

    public Resource newResource(int resourceId){
        Resource resource = new Resource(resourceId);
        initialize(resource, resourceId);
        return resource;
    }

    private void initialize(Resource resource, int resourceId) {
        if (resourceId ==0){return;}
        String[] resourceData = this.ResourceData[resourceId];

        resource.setName(resourceData[1]);
        resource.setDescription(resourceData[2]);
        resource.setHarvestTime(Integer.parseInt(resourceData[3]));
        resource.setRespawnTime(Integer.parseInt(resourceData[4]));
        resource.setHarvestToolId(ReadCSV.readIntList(resourceData[5]));
        resource.setItemDropId(ReadCSV.readIntList(resourceData[6]));
        resource.setItemDropMin(ReadCSV.readIntList(resourceData[7]));
        resource.setItemDropMax(ReadCSV.readIntList(resourceData[8]));
        if (resource.getRespawnTime() >= 0){
            resource.setCanRespawn(true);
        }
        if (resource.getHarvestTime() >= 0){
            resource.setCanHarvest(true);
        }
    }
}
